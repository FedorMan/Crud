package com.examplecrud.demo.entity;

import java.util.LinkedList;
import java.util.List;

public class CommitInstance {
    private List commitInstances;
    public CommitInstance(){
        commitInstances = new LinkedList();
    }
    public void add(Document document){
        commitInstances.add(document);
    }

    public List getCommitInstances() {
        return commitInstances;
    }

    public void setCommitInstances(List commitInstances) {
        this.commitInstances = commitInstances;
    }

    public CommitInstance(List commitInstances) {
        this.commitInstances = commitInstances;
    }
}
