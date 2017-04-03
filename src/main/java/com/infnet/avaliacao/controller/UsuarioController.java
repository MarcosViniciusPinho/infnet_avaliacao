package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.facade.IUsuarioFacade;
import com.infnet.avaliacao.controller.util.PathConstant;
import com.infnet.avaliacao.dto.impl.UsuarioDTO;
import com.infnet.avaliacao.entity.domain.PerfilEnum;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String LISTAR_USUARIOS = "listarUsuarios";
    private static final String LISTAR_PERFIS = "listarPerfis";

    @Resource
    private IUsuarioFacade usuarioFacade;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ModelAndView onList(Pageable pageable){
        ModelAndView mv = new ModelAndView(getViewList());
//        mv.addObject(LISTAR_USUARIOS, this.usuarioFacade.findAll());
        mv.addObject(LISTAR_USUARIOS, this.usuarioRepository.findAll(pageable));
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
        return PathConstant.PATH_USUARIO;
    }

}