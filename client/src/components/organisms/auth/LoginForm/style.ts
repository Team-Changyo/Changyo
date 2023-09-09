import { styled } from 'styled-components';

export const LoginFormContainer = styled.div<{ $saveLoginState: boolean }>`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	gap: 2rem;

	.login-input {
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		gap: 0.7rem;

		.save-login-state-btn {
			text-align: initial;
			padding: 0;
			display: flex;
			align-items: center;
			color: var(--gray-500);

			svg {
				width: 24px;
				width: 24px;

				fill: ${({ $saveLoginState }) => ($saveLoginState ? 'var(--main-color)' : 'var(--gray-400)')};
			}
		}
	}

	.btn-group {
		display: flex;
		flex-direction: column;
		gap: 0.7rem;
		width: 100%;

		.menu {
			text-align: center;
			.bar {
				font-size: 0.8rem;
				color: var(--gray-500);
				margin: 0 1rem;
			}
		}
	}
`;
