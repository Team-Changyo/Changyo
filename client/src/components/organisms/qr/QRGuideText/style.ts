import { styled } from 'styled-components';

export const QRGuideTextContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	gap: 10px;

	.message {
		font-size: 1.2rem;
		color: var(--gray-400);
	}

	.remain-time {
		font-size: 1.1rem;
		color: var(--gray-500);

		.time-text {
			font-size: 1.1rem;
			color: var(--danger-color);
		}
	}
`;
