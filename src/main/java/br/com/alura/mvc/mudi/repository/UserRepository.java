package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.User;

public interface UserRepository {

	User findByName(String name);

}
