package org.example.control;
import org.example.model.dao.DatabaseAdd;
import org.example.model.user.User;

public class UserManager {
    private static DatabaseAdd add;

    public static void adicionaNoBanco(User user){
         add = new DatabaseAdd();
         add.adicionaPessoa(user);

    }
    public static void atualizarSenha(String email, String senha){
        DatabaseAdd.trocaSenha(email,senha);
    }
}
