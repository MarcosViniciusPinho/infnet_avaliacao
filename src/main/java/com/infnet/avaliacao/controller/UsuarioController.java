package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.UsuarioFacade;
import com.infnet.avaliacao.controller.util.ApplicationConstant;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Classe responsável pela captura das informações na view.
 */
@Controller
@RequestMapping(value = PathConstant.PATH_USUARIO)
public class UsuarioController extends CadastroController<UsuarioDTO>{

    @Resource
    private UsuarioFacade usuarioFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onList(Pageable pageable){
        ModelAndView mv = new ModelAndView(getViewList());
        mv.addObject(ApplicationConstant.LISTAR_USUARIOS, this.usuarioFacade.findAllPaginated(pageable));
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
    protected void onDelete(Long id) {
        this.getFacade().delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLoadView(Model model){
        model.addAttribute(ApplicationConstant.LISTAR_PERFIS, this.usuarioFacade.findAllPerfil());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UsuarioFacade getFacade() {
        return usuarioFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPathView() {
        return PathConstant.PATH_USUARIO;
    }

}