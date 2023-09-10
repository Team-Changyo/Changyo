import { styled } from 'styled-components';

export const CertModalContainer = styled.div`
	border-radius: var(--radius-m);
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 90vw;
	max-width: 470px;
	height: 60vh;
	background-color: var(--white-color);
	padding: 20px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;

	.content {
		margin-top: 2rem;
		display: flex;
		flex-direction: column;
		gap: 2rem;

		h2 {
			font-size: 1.4rem;

			b {
				color: var(--main-color);
			}
		}

		.cert-input {
			margin-top: 2rem;
		}
	}

	.btn-group {
		display: flex;
		flex-direction: column;
		gap: 10px;
	}
`;
