package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.IUsuarioFacade;
import com.infnet.avaliacao.dto.UsuarioDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe responsável pelo gerenciamento entre a view e a camada business.
 */
@Controller
@RequestMapping(value = UsuarioController.PATH_USUARIO)
public class UsuarioController extends CrudController<UsuarioDTO>{

    private static final String LISTAR_USUARIOS = "listarUsuarios";
    protected static final String PATH_USUARIO = "/cadastro/usuario";

    @Autowired
    private IUsuarioFacade usuarioFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onList(){
        ModelAndView mv = new ModelAndView(getViewList());
        mv.addObject(LISTAR_USUARIOS, this.usuarioFacade.findAll());
        return mv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onPrepareCreate(){
        ModelAndView mv = new ModelAndView(getViewForm());
        this.onLoadView(mv);
        mv.addObject(new UsuarioDTO());
        return mv;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onPrepareUpdateOrDetail(String view, Long id){
        ParameterExceptionUtil.validateObjectNull(view);
        ParameterExceptionUtil.validateObjectNull(id);
        ModelAndView mv = new ModelAndView(view);
        this.onLoadView(mv);
        mv.addObject(this.usuarioFacade.findById(id));
        return mv;
    }

    /**
     * Método criado para retirar duplicação de código nos métodos 'onPrepareCreate' e 'onPrepareUpdateOrDetail'
     * @param mv mv
     */
    private void onLoadView(ModelAndView mv){
        mv.addObject(LISTAR_USUARIOS, this.usuarioFacade.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IUsuarioFacade getFacade() {
        return usuarioFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPathView() {
        return PATH_USUARIO;
    }

}