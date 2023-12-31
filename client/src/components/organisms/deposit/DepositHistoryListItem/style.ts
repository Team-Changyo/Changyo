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
			fill: ${({ $isDone }) => ($isDone ? 'var(--gray-400)' : 'var(--main-color)')};
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

		div {
			span {
				font-size: 0.9rem;
				color: ${({ $isDone }) => ($isDone ? 'var(--gray-400)' : 'var(--main-color)')};
			}
		}

		.title {
			font-weight: bold;
		}
		.member-name {
			color: ${({ $isDone }) => ($isDone ? 'var(--gray-400)' : 'var(--gray-500)')};
		}

		.money {
			color: ${({ $isDone }) => ($isDone ? 'var(--gray-400)' : 'var(--gray-500)')};
		}

		.return-datetime {
			color: ${({ $isDone }) => ($isDone ? 'var(--gray-400)' : 'var(--gray-500)')};
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
