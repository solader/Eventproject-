package com.projetFe.Event;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store the file as a byte array
    @Lob
    @Column(name = "file_data",columnDefinition = "LONGBLOB", nullable = false)
    private byte[] file;

    public Image() {
    }

    public Image(byte[] file) {
        this.file = file;
    }
    public Long getId() {
        return id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    @ManyToOne
    @JoinColumn(name = "imageEvent")
    private Event eventI ;







}
