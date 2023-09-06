import { styled } from 'styled-components';

export const QRListItemContainer = styled.div`
	height: 4rem;
	max-height: 64px;
	display: flex;
	flex-direction: row;

	&:hover {
		cursor: pointer;
		border-radius: var(--radius-s);
		background-color: var(--gray-100);
	}

	.qr-logo {
		width: 3rem;
		display: flex;
		align-items: center;
		justify-content: center;

		svg {
			width: 2.5rem;
			height: 2.5rem;
			fill: var(--main-color);
		}
	}

	.qr-info {
		display: flex;
		flex-direction: column;
		justify-content: center;
		gap: 5px;
		flex-grow: 1;
		padding-left: 10px;

		.title {
			span {
				color: var(--main-color);
			}
		}
	}

	.account {
		font-size: 0.9rem;
		color: var(--gray-500);

		span {
			font-size: 0.9rem;
			color: var(--gray-400);
		}
	}

	.money-unit {
		font-size: 0.9rem;
		color: var(--gray-500);

		span {
			font-size: 0.9rem;
			color: var(--gray-400);
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
