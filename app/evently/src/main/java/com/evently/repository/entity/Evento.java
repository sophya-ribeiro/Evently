package com.evently.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 128)
    private String nome;

    @Column(nullable = false, length = 255)
    private String descricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataInicio;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private Integer duracao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private LocalEvento local;

    @ManyToOne
    @JoinColumn(name = "organizador_id", nullable = false)
    private Organizador organizador;

    // construtores
    public Evento(String nome, String descricao, LocalDate dataInicio, LocalTime horaInicio, Integer duracao, BigDecimal preco, LocalEvento local, Organizador organizador) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.duracao = duracao;
        this.preco = preco;
        this.local = local;
        this.organizador = organizador;
    }

    public Evento(){
        
    }
    

    // getter e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return dataInicio;
    }

    public void setData(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalTime getHora() {
        return horaInicio;
    }

    public void setHora(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
