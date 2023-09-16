import React from 'react';
import Button from 'components/organisms/common/Button';
import { useNavigate } from 'react-router-dom';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import { styled } from 'styled-components';
import PageLayout from 'layouts/common/PageLayout';
import PageNotFoundLottie from 'components/atoms/lottie/PageNotFoundLottie';

const PageNotFoundLayout = styled.div`
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

function PageNotFound() {
	const navigate = useNavigate();

	return (
		<PageLayout>
			<PageNotFoundLayout>
				<SubTabNavbar text="페이지를 찾을 수 없습니다" closePath="/" type="close" />
				<div className="lottie">
					<PageNotFoundLottie />
				</div>
				<div className="message">
					<h2 className="color">페이지를 찾을 수 없습니다</h2>
					<h2>입력하신 주소가 정확한지 다시 한 번 확인해주세요.</h2>
				</div>

				<div className="next-btn">
					<Button handleClick={() => navigate('/')} type="Normal" text="챙겨요 홈" />
				</div>
			</PageNotFoundLayout>
		</PageLayout>
	);
}

export default PageNotFound;
