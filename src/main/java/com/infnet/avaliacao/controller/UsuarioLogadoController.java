package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.UsuarioFacade;
import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.MessageConstant;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.controller.util.ViewConstant;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.exception.BusinessException;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = PathConstant.PATH_USUARIO_LOGADO)
public class UsuarioLogadoController{

    @Resource
    private UsuarioFacade usuarioFacade;

    /**
     * Método que salva/altera uma entidade.
     * @param usuarioDTO usuarioDTO
     * @param redirectAttributes redirectAttributes
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_SAVE, method = RequestMethod.POST)
    public String save(UsuarioDTO usuarioDTO, RedirectAttributes redirectAttributes, Model model){
        try{
            this.getFacade().save(usuarioDTO);
            redirectAttributes.addFlashAttribute(MessageConstant.SUCESS, MessageConstant.MENSAGEM_SUCESSO_USUARIO_LOGADO);
            return ActionConstant.REDIRECT + ActionConstant.ACTION_HOME;
        } catch (ConstraintViolationException ex) {//NOSONAR a exceção é direcionada para a camada de visão da aplicação, portanto o uso do 'throw' neste ponto não é utilizado. - Validação de campos obrigatórios
            model.addAttribute(MessageConstant.SET_EXCEPTION_REQUIRED, ex.getConstraintViolations());
        } catch (BusinessException ex){//NOSONAR a exceção é direcionada para a camada de visão da aplicação, portanto o uso do 'throw' neste ponto não é utilizado. - Validação de negócio
            model.addAttribute(MessageConstant.SET_EXCEPTION_BUSINESS, ex.getMultipleMessages());
        }
        return getViewForm();
    }

    /**
     * Método que redireciona o usuário para a tela de alterar.
     * @param id id
     * @param model model
     * @return String
     */
    @RequestMapping(value = ActionConstant.ACTION_EDIT)
    public String prepareUpdate(@PathVariable Long id, Model model){
        ParameterExceptionUtil.validateObjectNull(id);
        ParameterExceptionUtil.validateObjectNull(model);
        model.addAttribute(this.usuarioFacade.findById(id));
        return getViewForm();
    }

    /**
     * {@inheritDoc}
     */
    public UsuarioFacade getFacade() {
        return usuarioFacade;
    }

    /**
     * {@inheritDoc}
     */
    public String getPathView() {
        return PathConstant.PATH_USUARIO_LOGADO;
    }

    /**
     * Método que redireciona para a tela de cadastrar/alterar.
     * @return String
     */
    public String getViewForm(){
        return getPathView() + ViewConstant.VIEW_FORM;
    }

}