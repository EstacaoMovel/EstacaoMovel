package pt.isec.gps.tp.utils;

import javafx.beans.property.SimpleStringProperty;

public class DetalhesContent {
    private SimpleStringProperty topico;
    private SimpleStringProperty detalheTopico;


    public DetalhesContent(String topico, String detalheTopico) {
        this.topico = new SimpleStringProperty(topico);
        this.detalheTopico = new SimpleStringProperty(detalheTopico);
    }

    public String getTopico() {
        return topico.get();
    }

    public SimpleStringProperty topicoProperty() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico.set(topico);
    }

    public String getDetalheTopico() {
        return detalheTopico.get();
    }

    public SimpleStringProperty detalheTopicoProperty() {
        return detalheTopico;
    }

    public void setDetalheTopico(String detalheTopico) {
        this.detalheTopico.set(detalheTopico);
    }
}
