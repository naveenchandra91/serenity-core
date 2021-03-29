package serenityscreenplay.net.serenitybdd.screenplay.shopping;

import serenitymodel.net.serenitybdd.core.exceptions.CausesCompromisedTestFailure;

public class PeopleAreTerriblyIncorrect extends AssertionError implements CausesCompromisedTestFailure {

    public PeopleAreTerriblyIncorrect(String message, Throwable cause) {
        super(message, cause);
    }

    public PeopleAreTerriblyIncorrect(String message) {
        super(message);
    }

    public PeopleAreTerriblyIncorrect(Throwable cause) {
        super(cause);
    }
}