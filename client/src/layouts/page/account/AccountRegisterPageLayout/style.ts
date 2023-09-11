import { styled } from 'styled-components';

export const AccountRegisterPageLayoutContainer = styled.div`
	height: calc(100vh - 5rem); // 100vh - navigation height
	position: relative;
	margin-top: 5rem;

	.title-mb {
		margin-bottom: 1rem;
	}

	.group-mb {
		margin-bottom: 2rem;
	}

	.bottom {
		position: absolute;
		width: 100%;
		bottom: 2rem;
		display: flex;
		flex-direction: column;
		align-items: center;
		gap: 10px;

		.check-is-main-account {
			width: fit-content;
		}

		.account-register-btn {
			width: 100%;
		}
	}
`;
