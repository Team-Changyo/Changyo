import { styled } from 'styled-components';

export const ViewQRPageLayoutContainer = styled.div`
	height: calc(100vh - 5rem); // 100vh - navigation height
	position: relative;
	margin-top: 5rem;

	.money-unit {
		text-align: center;
		margin: 1.5rem 0;
	}

	.qr-image {
		text-align: center;
		img {
			border-radius: 5px;
			width: 100%;
			max-width: 400px;
		}
	}

	.share-btns {
		width: 100%;
		position: absolute;
		bottom: 0;
		margin-bottom: 2rem;

		.qr-share-btn {
			margin-bottom: 1rem;
		}

		.link-share-btn {
			text-align: center;
		}
	}
`;
