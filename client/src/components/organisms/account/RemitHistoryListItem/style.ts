import { styled } from 'styled-components';

export const RemitHistoryListItemContainer = styled.div`
	height: 3rem;
	max-height: 64px;
	display: flex;
	flex-direction: row;

	.history-logo {
		width: 3rem;
		display: flex;
		align-items: center;
		justify-content: center;

		svg {
			fill: var(--main-color);
			width: 2.5rem;
			height: 2.5rem;
		}
	}

	.history-info {
		display: flex;
		flex-direction: column;
		justify-content: center;
		gap: 5px;
		flex-grow: 1;
		padding-left: 10px;

		.title {
			color: var(--gray-500);
		}

		.time {
			font-size: 0.9rem;
			color: var(--gray-400);
		}
	}

	.history-money-info {
		display: flex;
		flex-direction: column;
		justify-content: center;
		text-align: right;

		.price {
			color: var(--gray-500);

			&.primary {
				color: var(--main-color);
			}
		}

		.balance {
			font-size: 0.9rem;
			color: var(--gray-400);
		}
	}
`;
