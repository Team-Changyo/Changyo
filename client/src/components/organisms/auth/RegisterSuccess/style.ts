import { styled } from 'styled-components';

export const RegisterSuccessContainer = styled.div`
	position: relative;
	margin-top: 3rem;
	height: calc(100vh - 4rem);

	display: flex;
	flex-direction: column;

	.message {
		margin-top: 7rem;
		display: flex;
		flex-direction: column;
		align-items: center;
		gap: 10px;

		.color {
			color: var(--main-color);
		}
		h2 {
			font-size: 1.3rem;
		}
	}
	.lottie {
		margin-top: 3rem;
	}
	.next-btn {
		position: absolute;
		width: 100%;
		bottom: 2rem;
	}
`;
