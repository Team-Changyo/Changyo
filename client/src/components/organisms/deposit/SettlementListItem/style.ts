import { styled } from 'styled-components';

export const SettlementListItemContainer = styled.div<{ $isReturned: boolean }>`
	height: 3rem;
	max-height: 64px;
	display: flex;
	flex-direction: row;

	&:hover {
		cursor: pointer;
		border-radius: var(--radius-s);
		background-color: var(--gray-100);
	}

	.settlement-logo {
		width: 3rem;
		display: flex;
		align-items: center;
		justify-content: center;

		svg {
			fill: ${({ $isReturned }) => ($isReturned ? 'var(--gray-500)' : 'var(--main-color)')};
			width: 2.5rem;
			height: 2.5rem;
		}
	}

	.settlement-info {
		display: flex;
		flex-direction: column;
		justify-content: center;
		gap: 5px;
		flex-grow: 1;
		padding-left: 10px;

		.depositor-name {
			span {
				color: var(--gray-400);
			}
		}

		.return-datetime {
			span {
				color: var(--gray-400);
			}
		}
	}

	.return-btn {
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
				color: var(--gray-300);
			}
		}

		.returned {
			background-color: var(--gray-400);
			color: var(--gray-100);

			&:hover {
				cursor: pointer;
				color: var(--gray-100);
			}
		}

		.before-return {
			background-color: var(--danger-color);
			color: var(--gray-100);
		}
	}
`;
