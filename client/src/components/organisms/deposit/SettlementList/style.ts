import styled from 'styled-components';

export const SettlementListContainer = styled.div`
	display: flex;
	flex-direction: column;
	padding-bottom: calc(56px + 5rem);
	gap: 10px;

	.top {
		display: flex;
		justify-content: space-between;
	}

	.tab-option {
		display: flex;
		align-items: center;
		cursor: pointer;

		div {
			display: flex;
		}

		.multi-return-btn {
			svg {
				fill: var(--gray-500);
				width: 20px;
				height: 20px;
			}

			span {
				line-height: 20px;
				color: var(--gray-500);
				font-size: 0.9rem;
			}
		}

		.select-all {
			color: var(--main-color);
		}
	}
`;
