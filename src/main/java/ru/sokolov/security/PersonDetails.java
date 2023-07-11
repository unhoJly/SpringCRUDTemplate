package ru.sokolov.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sokolov.models.Person;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {
    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: возвращаем роли (допуски) пользователя
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getFullName();
    }

    // TODO: прикрутить срок годности аккаунта
    // достаём из базы время регистрации и смотрим сколько времени прошло с этого момента
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // TODO: прикрутить функцию блокировки аккаунта
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // TODO: прикрутить срок годности пароля
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Нужно, чтобы получать данные аутентифицированного пользователя
    public Person getPerson() {
        return this.person;
    }
}