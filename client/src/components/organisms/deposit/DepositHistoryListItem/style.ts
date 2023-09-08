import { styled } from 'styled-components';

export const HistoryListItemContainer = styled.div<{ $isDone: boolean }>`
	height: 4rem;
	max-height: 64px;
	display: flex;
	flex-direction: row;

	&:hover {
		cursor: pointer;
		border-radius: var(--radius-s);
		background-color: var(--gray-100);
	}

	.history-logo {
		width: 3rem;
		display: flex;
		align-items: center;
		justify-content: center;

		svg {
			fill: ${({ $isDone }) => ($isDone ? 'var(--gray-500)' : 'var(--main-color)')};
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

		svg {
			width: 25px;
			height: 25px;
		}
	}
`;
