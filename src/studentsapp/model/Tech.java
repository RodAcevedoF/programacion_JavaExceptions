package studentsapp.model;

public enum Tech {
    PYTHON, JAVA, C, JAVASCRIPT, GO, PHP, RUST, COBOL, SCALA, NODE, SOLIDITY, PASCAL, PERL, SWIFT;

    @Override
    public String toString() {
        // Capitalizar primera letra y resto minúsculas
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    public static void printTechs() {
        System.out.println(String.join(", ",
                java.util.Arrays.stream(Tech.values())
                        .map(Tech::toString)
                        .toArray(String[]::new)
        ));
    }

}


