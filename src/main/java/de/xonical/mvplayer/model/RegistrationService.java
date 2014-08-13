package de.xonical.mvplayer.model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class RegistrationService {

    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction et;
//
    @PostConstruct
    public void init() {
        this.emf = Persistence.createEntityManagerFactory("mvplayer");
        this.em = this.emf.createEntityManager();
        this.et = this.em.getTransaction();
    }
//
    public List<Directory> allDirectories() {
        return this.em.createNamedQuery(Directory.findAll).getResultList();
    }

//
    public Directory save(Directory directory) {
        et.begin();
        Directory merged = this.em.merge(directory);
        et.commit();
        return merged;
    }

    public void save() {
        et.begin();
        em.flush();
        et.commit();
    }

    public void remove(Directory directory) {
        et.begin();
        this.em.remove(directory);
        et.commit();
    }

    //---------------------------------------------------------------------

	public List<VideoFile> allVideoFiles() {
		return this.em.createNamedQuery(VideoFile.findAll).getResultList();
	}

	public VideoFile save(VideoFile videoFile) {
		et.begin();
		VideoFile merged = this.em.merge(videoFile);
		et.commit();
		return merged;
	}

	public void remove(VideoFile videoFile) {
		et.begin();
		this.em.remove(videoFile);
		et.commit();
	}

	//--------------------------------------------------------------------

    public void close() {
        et.begin();
        et.commit();
        em.close();
    }
}
