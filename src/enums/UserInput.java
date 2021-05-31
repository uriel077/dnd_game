package enums;

public enum UserInput {
    Up("w"),
    Down("s"),
    Left("a"),
    Right("d"),
    CastAbility("e"),
    Wait("q");

    private String key;
    UserInput(String key) {
        this.key=key;
    }

    // getter method
    public String getKey()
    {
        return this.key;
    }
    public String getRegex(){
        String regex="";
        for(UserInput inp : UserInput.values())
        {
            regex+=inp.getKey();
        }
        return regex;
    }
    public static UserInput findByKey(String key){
        for(UserInput v : values()){
            if( v.key.equals(key)){
                return v;
            }
        }
        return null;
    }

}
