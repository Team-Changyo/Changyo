import { styled } from 'styled-components';

export const RemittanceRequestInfoContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	gap: 8px;

	.deposit-title-info {
		color: var(--gray-500);

		.title {
			color: var(--main-color);
		}
	}
	.message {
		color: var(--gray-500);
		font-size: 1.8rem;
	}

	.account-info {
		color: var(--gray-400);
	}
`;
