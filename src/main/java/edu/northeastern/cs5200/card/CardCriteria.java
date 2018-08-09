package edu.northeastern.cs5200.card;

import org.springframework.stereotype.Component;

@Component
public class CardCriteria {
    private int pageSize = 1000;
    private String superType = "Pokémon";
    private String subType;
    private String setCode;
    private String name;
    private String weaknesses;
    private String attackDamage;
    private String attackCost;
    private String retreatCost;
    private String hp;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSuperType() {
        return superType;
    }

    public void setSuperType(String superType) {
        this.superType = superType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(String attackDamage) {
        this.attackDamage = attackDamage;
    }

    public String getAttackCost() {
        return attackCost;
    }

    public void setAttackCost(String attackCost) {
        this.attackCost = attackCost;
    }

    public String getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(String retreatCost) {
        this.retreatCost = retreatCost;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }
}
