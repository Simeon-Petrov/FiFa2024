package com.sirma.model;

public class Team {
    private int id;
    private String name;
    private String managerFullName;
    private String group;


    public Team(int id, String name, String managerFullName, String group) {
        this.id = id;
        this.name = name;
        this.managerFullName = managerFullName;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManagerFullName() {
        return managerFullName;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Team{id=" + id + ", name=" + name + '}';
    }
}