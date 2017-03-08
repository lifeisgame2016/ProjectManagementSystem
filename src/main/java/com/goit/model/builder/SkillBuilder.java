package com.goit.model.builder;

import com.goit.model.Skill;



public final class SkillBuilder {
    private Integer id;
    private String name;
    private Integer developerId;

    private SkillBuilder() {
    }

    public static SkillBuilder aSkill() {
        return new SkillBuilder();
    }

    public SkillBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public SkillBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SkillBuilder withDeveloperId(Integer developerId) {
        this.developerId = developerId;
        return this;
    }

    public Skill build() {
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        skill.setDeveloperId(developerId);
        return skill;
    }
}
