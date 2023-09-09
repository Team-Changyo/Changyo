import styled from 'styled-components';

export const MultiReturnMenuWrapper = styled.div`
	position: fixed;
	right: 0;
	left: 0;
	bottom: 0;
	z-index: 30;

	.multi-return-menu-container {
		display: flex;
		flex-direction: column;
		gap: 10px;
		background-color: var(--white-color);
		border: 2px solid var(--gray-100);
		border-radius: var(--radius-m);
		border-bottom: none;
		padding: 15px 15px;
		width: 100%;
		max-width: 500px;
		margin: 0 auto;
	}
`;

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
