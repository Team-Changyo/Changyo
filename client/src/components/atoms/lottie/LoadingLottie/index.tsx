import React from 'react';
import Lottie, { Options } from 'react-lottie';
import LoadingAnimation from './lottie.json';

function LoadingLottie() {
	const defaultOptions: Options = {
		loop: true,
		autoplay: true,
		animationData: LoadingAnimation,
	};

	return <Lottie options={defaultOptions} width={350} height={350} />;
}

export default LoadingLottie;
