class LoginForm extends React.Component {
	  constructor(props) {
	    super(props);
	    this.stateLogin = {login: ''};
	    this.stateSenha = {senha: ''};

	    this.handleChangeLogin = this.handleChangeLogin.bind(this);
	    this.handleChangeSenha = this.handleChangeSenha.bind(this);
	    this.handleSubmit = this.handleSubmit.bind(this);
	  }
	  
	  handleChangeLogin(event) {
	    this.setStateLogin({login: event.target.value});
	  }
	  
	  handleChangeSenha(event) {
		    this.setStateSenha({senha: event.target.value});
	  }

	  handleSubmit(event) {
	    alert('Um nome foi enviado: ' + this.state.value);
	    event.preventDefault();
	  }

	  render() {
	    return(
	  <div class="limiter">
		<div class="container-login100"> 
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" onSubmit={this.handleSubmit}>
					<span class="login100-form-title p-b-33">
						Acesso
					</span>  

					<div class="wrap-input100">
						<input class="input100" type="text" name="login" placeholder="Login" value={this.stateLogin.login} onChange={this.handleChangeLogin}>
						</input>
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="wrap-input100 rs1 validate-input" data-validate="Password is required">
						<input class="input100" type="password" name="pass" placeholder="Senha" value={this.stateSenha.senha} onChange={this.handleChangeSenha} >
						</input>
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div> 

					<div class="container-login100-form-btn m-t-20">
						<button class="login100-form-btn">
							Acessar
						</button>
					</div>

					<div class="text-center p-t-45 p-b-4">
						<span class="txt1">
							Esqueceu a senha
						</span>

						<a href="#" class="txt2 hov1">
							Usuário / Senha?
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>);
	  }
	}

ReactDOM.render(
		  <LoginForm />,
		  document.getElementById('loginReactForm')
		);
