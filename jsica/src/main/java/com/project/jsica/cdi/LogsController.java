package com.project.jsica.cdi;

import com.project.jsica.ejb.entidades.Logs;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

@Named(value = "logsController")
@ViewScoped
public class LogsController extends AbstractController<Logs> {

    public LogsController() {
        // Inform the Abstract parent controller of the concrete Logs?cap_first Entity
        super(Logs.class);
    }

}
