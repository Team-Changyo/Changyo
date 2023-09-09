import { styled } from 'styled-components';

export const LoginPageLayoutContainer = styled.div`
	position: relative;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100vh;

	.changyo-logo {
		position: absolute;
		margin-bottom: 30rem;

		svg {
			width: 300px;
		}
	}

	.login-form {
		width: 100%;
	}

	.shinhan-bank-logo {
		position: absolute;
		height: 30px;
		bottom: 2rem;

		svg {
			width: 100px;
			height: 30px;
		}
	}
`;
