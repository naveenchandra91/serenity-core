package serenityscreenplay.net.serenitybdd.screenplay.questions;

import serenityscreenplay.net.serenitybdd.screenplay.Actor;
import serenityscreenplay.net.serenitybdd.screenplay.Question;
import serenityscreenplay.net.serenitybdd.screenplay.annotations.Subject;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

@Subject("the minimum value of #listQuestion")
public class MinQuestion<T extends Object & Comparable<? super T>> implements Question<T> {

    private final Question<? extends Collection<T>> listQuestion;
    private final Comparator<? super T> comparator;

    public MinQuestion(Question<? extends Collection<T>> listQuestion) {
        this(listQuestion, null);
    }

    public MinQuestion(Question<? extends Collection<T>> listQuestion, Comparator<? super T> comparator) {
        this.listQuestion = listQuestion;
        this.comparator = comparator;
    }

    @Override
    public T answeredBy(Actor actor) {
        return Collections.min(listQuestion.answeredBy(actor), comparator);
    }
}