class FinalResult<name, health,score,status> {
    private name name;
    private health health;
    private score score;
    private status status;
    public FinalResult(name name, health health,score score,status status) {
        this.name = name;
        this.health = health;
        this.score = score;
        this.status = status;
    }

    public name getName() {
        return name;
    }

    public health getHealth() {
        return health;
    }
    public score getScore() {
        return score;
    }
    public status getStatus() {
        return status;
    }
}
