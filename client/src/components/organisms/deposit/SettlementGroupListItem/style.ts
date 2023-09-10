import { styled } from 'styled-components';

export const SettlementGroupListItemContainer = styled.div`
	height: 4rem;
	max-height: 64px;
	display: flex;
	flex-direction: row;

	&:hover {
		cursor: pointer;
		border-radius: var(--radius-s);
		background-color: var(--gray-100);
	}

	.left {
		display: flex;
		flex-direction: column;
		justify-content: center;
		gap: 5px;
		flex-grow: 1;
		padding-left: 10px;

		.title {
			color: var(--main-color);
		}

		.money-unit-row {
			font-size: 0.9rem;
			color: var(--gray-500);

			span {
				color: var(--gray-400);
			}
		}

		.before-return-total-row {
			font-size: 0.9rem;
			color: var(--gray-500);

			span {
				color: var(--gray-400);
			}
		}
	}

	.right {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;

		svg {
			width: 25px;
			height: 25px;
		}

		.before-return {
			color: var(--main-color);
		}

		.after-return {
			color: var(--gray-500);
		}
	}
`;
