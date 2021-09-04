package com.insurtech.review.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean show;
    private boolean showComments;
    private boolean showVotes;
    private boolean saveComment;
    private boolean saveVote;

    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    private Set<Vote> votes = new HashSet<>();

    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    private Set<Comment> comments =  new HashSet<>();

    public Product() {
    }

    public Product(boolean show, boolean showComments, boolean showVotes, boolean saveComment, boolean saveVote) {
        this.show = show;
        this.showComments = showComments;
        this.showVotes = showVotes;
        this.saveComment = saveComment;
        this.saveVote = saveVote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShowComments() {
        return showComments;
    }

    public void setShowComments(boolean showComments) {
        this.showComments = showComments;
    }

    public boolean isShowVotes() {
        return showVotes;
    }

    public void setShowVotes(boolean showVotes) {
        this.showVotes = showVotes;
    }

    public boolean isSaveComment() {
        return saveComment;
    }

    public void setSaveComment(boolean saveComment) {
        this.saveComment = saveComment;
    }

    public boolean isSaveVote() {
        return saveVote;
    }

    public void setSaveVote(boolean saveVote) {
        this.saveVote = saveVote;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", show=" + show +
                ", showComments=" + showComments +
                ", showVotes=" + showVotes +
                ", saveComment=" + saveComment +
                ", saveVote=" + saveVote +
                '}';
    }
}
