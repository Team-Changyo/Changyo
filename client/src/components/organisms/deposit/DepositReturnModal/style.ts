import { styled } from 'styled-components';

export const DepositReturnModalContainer = styled.div`
	border-radius: var(--radius-m);
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 90vw;
	max-width: 470px;
	height: 80vh;
	background-color: var(--white-color);
	padding: 20px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;

	.content {
		margin-top: 3rem;
		display: flex;
		flex-direction: column;
		gap: 2rem;

		h2 {
			font-size: 1.5rem;

			b {
				color: var(--main-color);
			}
		}
	}

	.money-unit {
		display: flex;
		flex-direction: column;
		gap: 5px;

		span {
			font-size: 0.9rem;
			color: var(--gray-400);
		}
	}

	.option {
		* {
			font-family: 'Pretendard' !important;
		}
		display: flex;
		flex-direction: column;
		gap: 20px;

		h3 {
			color: var(--gray-500);
			font-size: 1.3rem;
		}
		.reason {
			b {
				color: var(--danger-color);
			}
		}

		.success {
			color: var(--success-color);
		}

		input {
			height: 40px;
		}
	}

	.btn-group {
		display: flex;
		flex-direction: column;
		gap: 10px;
	}
`;
