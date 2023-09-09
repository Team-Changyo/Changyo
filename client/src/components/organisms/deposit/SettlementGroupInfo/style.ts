import { styled } from 'styled-components';

export const SettlementGroupInfoContainer = styled.div`
	color: var(--gray-500);
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	gap: 5px;

	.title {
		font-size: 1.1rem;

		span {
			font-size: 1.1rem;
			color: var(--main-color);
		}
	}

	.money-unit {
		span {
			color: var(--gray-400);
		}
	}

	.before-return-total {
		span {
			color: var(--gray-400);
		}
	}
`;
