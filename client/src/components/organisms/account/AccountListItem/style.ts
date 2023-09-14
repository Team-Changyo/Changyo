import { styled } from 'styled-components';

export const AccountListItemContainer = styled.div`
	height: 3rem;
	max-height: 64px;
	display: flex;
	flex-direction: row;

	&:hover {
		cursor: pointer;
		border-radius: var(--radius-s);
		background-color: var(--gray-100);
	}

	.bank-logo {
		width: 3rem;
		display: flex;
		align-items: center;
		justify-content: center;

		svg {
			width: 2.5rem;
			height: 2.5rem;
		}
	}

	.account-info {
		display: flex;
		flex-direction: column;
		justify-content: center;
		gap: 5px;
		flex-grow: 1;
		padding-left: 10px;

		.alias {
			color: var(--black-100);
		}

		.bankname {
			color: var(--gray-400);
		}
		.balance {
			color: var(--black-500);
			font-weight: bold;
		}
	}

	.detail-btn {
		width: 3rem;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;

		button {
			border: none;
			background-color: var(--gray-200);
			border-radius: var(--radius-l);
			font-size: 0.7rem;
			font-weight: bold;
			padding: 5px 10px;
			color: var(--black-100);

			&:hover {
				cursor: pointer;
				color: var(--main-color);
			}
		}
	}
`;
