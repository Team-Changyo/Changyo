import { styled } from 'styled-components';

export const DepositRetrunModalWrapper = styled.div`
	width: 100%;

	.deposit-return-modal-container {
		width: 90vw;
		max-width: 470px;
		height: 70vh;
		padding: 20px 30px;
		background: var(--white-color);
		border-radius: var(--radius-m);
		display: flex;
		flex-direction: column;
		justify-content: space-between;

		.content {
			margin-top: 3rem;

			h2 {
				font-size: 1.4rem;

				span {
					font-size: 1.4rem;
					color: var(--main-color);
				}
			}
			display: flex;
			flex-direction: column;
			gap: 2rem;
		}
		.done-btn {
			display: flex;
			flex-direction: column;
			gap: 10px;
		}
	}
`;
