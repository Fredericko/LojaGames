package controle.crud_usuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import negocio.Usuario;
import vo.UsuarioVO;
import vo.excecao.UsuarioVOException;

@ManagedBean
@SessionScoped
public class UsuarioControle {

	private Usuario usuario;
	private UsuarioVO vo;
	private String login;
	private String senha;

	public UsuarioControle() {
		usuario = new Usuario();
		vo = new UsuarioVO();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioVO getVo() {
		return vo;
	}

	public void setVo(UsuarioVO vo) {
		this.vo = vo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String login(ActionEvent event) {
		try {
			vo = usuario.getByLoginSenha(login, senha);
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage("Usu�rio " + login + " logado com sucesso!"));
			
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage(e.getMessage()));
		}
		
		return "perfil_m2";
	}

	public void cadastrarUsuario(ActionEvent event) {
		try {
			vo.setCredito(1000.50);
			usuario.save(vo);
			vo = new UsuarioVO();
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage("Usu�rio Cadstrado com sucesso!"));
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage(e.getMessage()));
		}
	}

	public void excluirProduto() {
		usuario.delete(vo);
	}
}