package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.IUsuarioFacade;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.domain.PerfilEnum;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = UsuarioController.PATH_USUARIO)
public class UsuarioController extends CrudController<UsuarioDTO>{

    private static final String LISTAR_USUARIOS = "listarUsuarios";
    private static final String LISTAR_PERFIS = "listarPerfis";
    protected static final String PATH_USUARIO = "/cadastro/usuario";

    @Resource
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
    protected String onPrepareCreate(Model model){
        this.onLoadView(model);
        model.addAttribute(new UsuarioDTO());
        return getViewForm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String onPrepareUpdateOrDetail(String view, Long id, Model model){
        ParameterExceptionUtil.validateObjectNull(view);
        ParameterExceptionUtil.validateObjectNull(id);
        this.onLoadView(model);
        model.addAttribute(this.usuarioFacade.findById(id));
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLoadView(Model model){
        model.addAttribute(LISTAR_PERFIS, PerfilEnum.values());
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