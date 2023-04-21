package com.bartlot.Server.entity;

// import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "action")
public class ActionEntity {
    @Id
    @Column(name = "act_code", length = 100, nullable = false)
    private String actCode;

    @Column(name = "url_code", length = 100, nullable = false)
    private String urlCode;

    @Column(name = "act_name", length = 100, nullable = false)
    private String actName;

    @Column(name = "act_description", length = 200, nullable = false)
    private String actDescription;

    @Column(name = "allow", nullable = false)
    private Boolean allow;

    @Column(name = "category", length = 100, nullable = false)
    private String category;

    @Column(name = "action_label", length = 100, nullable = false)
    private String actionLabel;

    @OneToMany(mappedBy = "actionEntity", fetch = FetchType.EAGER)
    // obtenir les instances de ProfilAction liées à une instance de ActionModel.
    private List<ProfilActionEntity> profileActions;

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActDescription() {
        return actDescription;
    }

    public void setActDescription(String actDescription) {
        this.actDescription = actDescription;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

}