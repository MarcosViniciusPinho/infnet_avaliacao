package com.infnet.avaliacao.controller.util;

public final class ActionConstant {//NOSONAR desnecessário implementação que acusa o sonar.

    public static final String ACTION_LIST = "/list";
    public static final String ACTION_EDIT = "/edit/{id}";
    public static final String ACTION_EDIT_CUSTOM = "/edit/{id}/avaliacao/{idAvaliacao}";
    public static final String ACTION_SAVE = "/save";
    public static final String ACTION_DETAIL = "/detail/{id}";
    public static final String ACTION_DELETE = "/delete/{id}";
    public static final String ACTION_ERROR = "/error/{id}";
    public static final String ACTION_ERROR_CUSTOM = "/error/{id}/avaliacao/{idAvaliacao}";
    public static final String ACTION_CREATE = "/create";
    public static final String ACTION_LINK_RESPONDER_AVALIACAO = "/aluno/{cpf}/turma/{id}";
    public static final String ACTION_AGRADECIMENTO = "/agradecimento";
    public static final String REDIRECT = "redirect:";

}
