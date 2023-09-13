import { styled } from 'styled-components';

export const RegisterFormContainer = styled.div<{ $isStore: boolean }>`
	display: flex;
	flex-direction: column;
	gap: 30px;
	margin-top: 4rem;

	.login-info {
		display: flex;
		flex-direction: column;
		gap: 15px;
	}

	.user-info {
		display: flex;
		flex-direction: column;
		gap: 15px;
	}

	.check-role {
		border-radius: var(--radius-s);
		border: 2px solid ${({ $isStore }) => ($isStore ? 'var(--main-color)' : 'var(--gray-300)')};
		padding: 0.5rem 1rem;
	}
`;
