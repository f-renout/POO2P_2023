package fr.unistra.l2.poo2.td4;

import java.util.EventListener;

public interface ChangementPositionListener extends EventListener
{
	void positionChanged(ChangementPositionEvent event);
}
