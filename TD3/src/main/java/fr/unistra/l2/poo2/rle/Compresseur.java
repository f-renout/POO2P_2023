package fr.unistra.l2.poo2.rle;

import lombok.RequiredArgsConstructor;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static fr.unistra.l2.poo2.rle.RLE.CTRL_POIDS_FORT;

@RequiredArgsConstructor
public class Compresseur {

    private final ColorConverter converter;

    public Queue<Byte> compresser(List<Color> image) {
        //la queue qui contiendra le resultat de la compression
        Queue<Byte> result = new LinkedList<>();
        //le buffer qui ca tenir la liste des couleurs en cours d'analyse
        Queue<Color> buffer = new LinkedList<>();
        //quelle est la derniere couleur lue
        Color couleurRepetee = null;
        //compteur sur combien de fois la derniere couleur est repetée
        short nbRepetitions = 0;

        //pour chaque pixel de notre image
        for (Color pixel : image) {
            // Si début d'une série
            if (nbRepetitions == 0) {
                couleurRepetee = pixel;
                nbRepetitions++;
            } else if (couleurRepetee == pixel) {
                // Si on est dans la répétition
                nbRepetitions++;
                //on "démarre" la compression
                if (nbRepetitions == 3) {
                    //nb represente le nombre de pixels qui auraient pu etre bufferises avant qu'on compresse
                    short nb = (short) (buffer.size() - 2); //-2 car en l33 on a incrementé le compteur (on regarde en fait juste si on a dans notre buffer autre chose que le pixel courant)
                    if (nb != 0) {
                        //par exemple si on a  BNBNBBBB -> dans le buffer on aura [B,N,B,N,B,B,B] => on doit "sauver"  [B,N,B,N] qui sont des pixels non compressé
                        //avant de continuer à "stocker" la suite de notre buffer

                        //on stocke les octets de controles = le nb de pixels non compressés qui vont etre inserés
                        stockePixelsNonCompresses(nb, result, buffer);
                    }
                }
            } else if (nbRepetitions > 2) { //on a changé de couleur et on etait dans une repetition induisant une compression et on change par ex on a lu 4 B et on lit N
                stockePixelCompresses(nbRepetitions, couleurRepetee, result);
                //on clear notre buffer
                buffer.clear();
                //et on traite le pixel courant => la couleur repetée est la nouvelle couleur lue et le nb de repet est 1
                couleurRepetee = pixel;
                nbRepetitions = 1;
            } else {
                //sinon on est dans un cas ou la repet c'est 1 ou 2
                //on met juste la couleur repetée
                couleurRepetee = pixel;
            }
            //on lit le pixel et on le rajoute au buffer
            buffer.add(pixel);
        }
        // On gère la fin (ce qui reste dans notre buffer une fois qu'on a fini de lire notre image)
        traiterBufferEnFinDeLecture(result, buffer, couleurRepetee, nbRepetitions);
        return result;
    }

    private void stockePixelCompresses(short nbRepetitions, Color couleurRepetee, Queue<Byte> result) {
        //on définit nos octets de controle
        //et le pixel qui est repetée
        List<Byte> couleurString = converter.toHexa(couleurRepetee);
        //on rajoute à notre chaine les octets de controle et le pixel repeté
        final var array = shortToArray(nbRepetitions,true);
        result.add(array[0]);
        result.add(array[1]);
        result.addAll(couleurString);
    }

    private static byte[] shortToArray(short nbRepetitions, boolean compresse) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        short toput = compresse?(short) (nbRepetitions | CTRL_POIDS_FORT):nbRepetitions;
        buffer.putShort(toput);
        return buffer.array();
    }

    private void stockePixelsNonCompresses(short nb, Queue<Byte> result, Queue<Color> buffer) {
        final var array = shortToArray(nb,false);
        result.add(array[0]);
        result.add(array[1]);
        final var uncompressedList = buffer.stream().limit(nb).map(converter::toHexa).flatMap(Collection::stream).toList();
        result.addAll(uncompressedList);
    }

    private void traiterBufferEnFinDeLecture(Queue<Byte> result, Queue<Color> buffer, Color couleurRepetee, short nbRepetitions) {
        //soit on a moins de 3 repet et on va stocket ce qu'on a dans le buffer sans compression
        if (nbRepetitions < 3) {
            short nb = (short) buffer.size();
            if (nb != 0) {
                stockePixelsNonCompresses(nb, result, buffer);
            }
            //soit on a 3 repets ou plus et la on a un pixel "compressé" dans notre buffer donc on le stocke
        } else {
            stockePixelCompresses(nbRepetitions, couleurRepetee, result);
        }
    }

}
