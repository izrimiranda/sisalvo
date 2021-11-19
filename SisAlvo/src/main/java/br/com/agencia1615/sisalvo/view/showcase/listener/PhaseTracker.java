package br.com.agencia1615.sisalvo.view.showcase.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

public class PhaseTracker implements javax.faces.event.PhaseListener {

	private static final long serialVersionUID = -6723797173046037566L;

	@Override
	public void afterPhase(PhaseEvent event) {
		event.getFacesContext().getExternalContext().log("AFTER: " + event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		event.getFacesContext().getExternalContext().log("BEFORE: " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
