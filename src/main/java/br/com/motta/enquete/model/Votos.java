package br.com.motta.enquete.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_votos")
public class Votos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voto_enquete_id", nullable = false)
    private Enquete enquete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opcao_id", nullable = false)
    private  OpcoesVoto opcoesVoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voto_usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "data_voto", nullable = false)
    private LocalDate dataVoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enquete getEnquete() {
        return enquete;
    }

    public void setEnquete(Enquete enquete) {
        this.enquete = enquete;
    }

    public OpcoesVoto getOpcoesVoto() {
        return opcoesVoto;
    }

    public void setOpcoesVoto(OpcoesVoto opcoesVoto) {
        this.opcoesVoto = opcoesVoto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataVoto() {
        return dataVoto;
    }

    public void setDataVoto(LocalDate dataVoto) {
        this.dataVoto = dataVoto;
    }
}
