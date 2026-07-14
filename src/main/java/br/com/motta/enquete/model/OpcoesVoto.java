package br.com.motta.enquete.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_opcoes_voto")
public class OpcoesVoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opcao_enquete_id", nullable = false)
    private Enquete enquete;

    @Column(name = "texto", nullable = false, length = 250)
    private String texto;

    @Column(name = "quantidade_votos", nullable = false)
    private Integer quantidadeVotos = 0;

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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getQuantidadeVotos() {
        return quantidadeVotos;
    }

    public void setQuantidadeVotos(Integer quantidadeVotos) {
        this.quantidadeVotos = quantidadeVotos;
    }
}
