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

	.multi-return-btn {
		display: flex;
		align-items: center;
		cursor: pointer;

		svg {
			fill: var(--main-color);
			width: 20px;
			height: 20px;
		}

		span {
			line-height: 20px;
			color: var(--main-color);
			font-size: 0.9rem;
		}

		div {
			display: flex;
		}
	}
`;
